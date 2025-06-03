import kotlinx.serialization.Serializable

@Serializable
data class ItemCarrinho(val peca: Peca, var quantidade: Int)