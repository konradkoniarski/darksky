package co.uk.darksky.model

data class Forecast(
        val longitude: String,
        val latitude: String,
        val timezone: String,
        val currently: Weather
) {

}
