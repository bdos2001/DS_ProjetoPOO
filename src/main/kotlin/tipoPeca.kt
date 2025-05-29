import kotlinx.serialization.Serializable

@Serializable
data class tipoPeca(
    val id: Int,
    var nome: String
) {
}