import kotlinx.serialization.Serializable

@Serializable
data class Cliente(
    val idCliente: Int = 0,
    val pessoa: Pessoa
) {
    override fun toString(): String {
        return """
        ==============================
        Número de Cliente: $idCliente
        ${pessoa}
        ==============================
    """.trimIndent()
    }

}