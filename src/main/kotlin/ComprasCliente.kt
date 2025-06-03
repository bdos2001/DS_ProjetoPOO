import kotlinx.serialization.Serializable
import kotlinx.serialization.Contextual

@Serializable
data class ComprasCliente(
    val idCompra: Int,
    val idCliente: Int,
    @Contextual
    val itensComprados: List<ItemCarrinho>,
    val precoTotal: Double
)
