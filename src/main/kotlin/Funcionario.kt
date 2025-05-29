import kotlinx.serialization.Serializable

@Serializable
data class Funcionario(
    val idFuncionario: Int = 0,
    val pessoa: Pessoa
) {
    override fun toString(): String {
        return """
        ==============================
        Número de Funcionário: $idFuncionario
        ${pessoa}
        ==============================
    """.trimIndent()
    }
}