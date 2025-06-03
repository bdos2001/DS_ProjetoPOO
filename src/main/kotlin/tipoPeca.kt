import kotlinx.serialization.Serializable

@Serializable
data class TipoPeca(
    val id: Int,
    var nome: String
)
