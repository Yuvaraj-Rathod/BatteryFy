package app.batteryfy.mobile.model


// --- Data Models for the UI ---
data class Station(
    val id: String,
    val name: String,
    val status: String,
    val powerOutput: String,
    val waitingTime: String,
    val pricePerKwh: String
)

data class Amenity(
    val id: String,
    val title: String,
    val description: String,
    val imageRes: Int
)

// Data model for this specific component
data class EVService(
    val id: String,
    val name: String,
    val description: String
)
