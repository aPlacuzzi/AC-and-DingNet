package it.unibo.protelis.model

interface LoRaHeader {
    val header: String
}

interface LoRaPayload {
    fun payloadToString(): String
}

data class SensorData(val sensorType: SensorType, val sensorValue: Double)

interface LoRaSensorPayload: LoRaPayload {
    val sensorsData: List<SensorData>

    val time: Int

    val position: GPSPosition?
}

interface LoRaUserPayload: LoRaSensorPayload {
    val destination: GPSPosition
}

data class EmptyLoRaHeader(override val header: String): LoRaHeader

data class LoRaSensorPayloadImpl(
    override val time: Int,
    override val sensorsData: List<SensorData>,
    override val position: GPSPosition? = null
    ) : LoRaSensorPayload {

    //TODO
    override fun payloadToString(): String = "$sensorsData"
}

sealed class LoRaMessage<H: LoRaHeader, P : LoRaPayload>(header: LoRaHeader, payload: P)

data class LoRaSensorMessage(val header: String = "", val payload: LoRaSensorPayloadImpl):
    LoRaMessage<EmptyLoRaHeader, LoRaSensorPayloadImpl>(
        EmptyLoRaHeader(header), payload)

data class LoRaUserPayloadImpl(
    override val time: Int,
    override val position: GPSPosition,
    override val destination: GPSPosition,
    override val sensorsData: List<SensorData> = emptyList()
): LoRaUserPayload {
    override fun payloadToString(): String = "[" +
            "source: $position" +
            "destination: $destination" +
            "sensorData: $sensorsData" +
            "]"
}

data class LoRaUserMessage(val header: String = "", val payload: LoRaUserPayloadImpl):
    LoRaMessage<EmptyLoRaHeader, LoRaUserPayloadImpl>(
        EmptyLoRaHeader(header), payload)