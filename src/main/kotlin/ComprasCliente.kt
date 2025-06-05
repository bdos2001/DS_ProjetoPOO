import kotlinx.serialization.Serializable
import kotlinx.serialization.Contextual

@Serializable
data class ComprasCliente(
    val idCompra: Int,
    val idCliente: Int,
    @Contextual
    val itensComprados: List<ItemCarrinho>,
    val precoTotal: Double,
    val dataCompra: String // formato dd/mm/aaaa
) {
    override fun toString(): String {
        val itensFormatados = itensComprados.joinToString("\n    ") { 
            "${it.peca.nome} - ${it.quantidade} unidade(s) x ${it.peca.preco}€ = ${it.quantidade * it.peca.preco}€" 
        }
        return """
            |Compra #$idCompra (Cliente ID: $idCliente) - Data: $dataCompra
            |Itens:
            |    $itensFormatados
            |Total: $precoTotal€
        """.trimMargin()
    }
}
