package co.uk.darksky;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import co.uk.darksky.model.Forecast;
import co.uk.darksky.network.ForecastApi;
import io.reactivex.Single;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

import static org.junit.Assert.assertTrue;

public class NetworkServiceTest {

    private static final String FORECAST_DATA = "{\n" +
            "  \"latitude\": 42.3601,\n" +
            "  \"longitude\": -71.0589,\n" +
            "  \"currently\": {\n" +
            "    \"icon\": \"sunny\",\n" +
            "    \"temperature\": \"60.48\",\n" +
            "    \"summary\":\"\"" +
            "  }\n" +
            "}";

    private MockWebServer mockWebServer;
    private ForecastApi service;

    @Before
    public void setUp() {
        mockWebServer = new MockWebServer();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("").toString())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        final Dispatcher dispatcher = new Dispatcher() {

            @Override
            public MockResponse dispatch(final RecordedRequest request) throws InterruptedException {

                if (request.getPath().equals("/forecast/360df1e319e95044fe7efd7c859c85b8/42.3601,-71.0589?units=si")) {
                    return new MockResponse().setResponseCode(200).setBody(FORECAST_DATA);
                }
                return new MockResponse().setResponseCode(404);
            }
        };
        mockWebServer.setDispatcher(dispatcher);
        service = retrofit.create(ForecastApi.class);
    }

    @Test
    public void testPost() throws IOException {
        final Single<Forecast> call = service.getForecast("360df1e319e95044fe7efd7c859c85b8", "42.3601", "-71.0589");
        call.subscribe((forecast, throwable) -> {
            assertTrue(forecast != null);
            assertTrue(forecast.getLongitude().equals("-71.0589"));
            assertTrue(forecast.getLatitude().equals("42.3601"));
            assertTrue(forecast.getCurrently().getIcon().equals("sunny"));
        });
    }

    @After
    public void close() throws IOException {
        mockWebServer.shutdown();
    }
}