import kotlinx.serialization.Serializable

@Serializable
data class Carro(
    var matricula: String,
    var modelo: Modelo,
    var ano: Int,
    var dono: Pessoa
) {
    override fun toString(): String {
        return """
        ==============================
        Matricula: $matricula
        Marca: ${modelo.marca.nome}
        Modelo: ${modelo.nome}
        Ano: $ano
        Dono: ${dono.nome}
        ==============================
    """.trimIndent()
    }
}