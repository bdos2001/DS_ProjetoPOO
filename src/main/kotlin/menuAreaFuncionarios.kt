fun menuAreaFuncionarios() {
    var finalizar = false
    while (!finalizar) {
        println("=====================================")
        println("Área dos Funcionários")
        println("=====================================")
        println("1. Adicionar funcionário")
        println("2. Listar funcionários")
        println("3. Editar funcionário")
        println("4. Remover funcionário")
        println("0. Voltar ao menu de admin")
        println("=====================================")
        print("Escolha uma opção: ")

        val opcao = readLine()!!.toInt()
        cls()
        when (opcao) {
            1 -> adicionarFuncionario()
            2 -> listarFuncionarios()
            3 -> editarFuncionario()
            4 -> removerFuncionario()
            0 -> finalizar = true
            else -> println("Opção inválida, tente novamente.")
        }
    }
}

fun adicionarFuncionario() {
    println("Adicionar funcionário")
    print("Nome: ")
    val nome = readLine()!!
    print("Morada: ")
    val morada = readLine()!!
    print("Contacto: ")
    val contacto = readLine()!!.toInt()
    print("NIF: ")
    val nif = readLine()!!.toInt()
    print("Email: ")
    val email = readLine()!!

    // Gera um ID único para a pessoa
    val idPessoa = if (listaPessoas.isEmpty()) 1 else listaPessoas.maxOf { it.idPessoa } + 1
    val pessoa = Pessoa(idPessoa, nome, morada, contacto, nif, email)
    listaPessoas.add(pessoa)

    // Gera um ID único para o funcionário
    val idFuncionario = if (listaFuncionarios.isEmpty()) 1 else listaFuncionarios.maxOf { it.idFuncionario } + 1
    val funcionario = Funcionario(idFuncionario, pessoa)
    listaFuncionarios.add(funcionario)

    println("Funcionário adicionado com sucesso!")
}

fun listarFuncionarios() {
    println("Listar funcionários")
    for (funcionario in listaFuncionarios) {
        println(funcionario)
    }
}

fun editarFuncionario() {
    println("Editar funcionário")
    print("ID do funcionário: ")
    val idFuncionario = readLine()!!.toInt()
    val funcionario = listaFuncionarios.find { it.idFuncionario == idFuncionario }
    if (funcionario != null) {
        var finalizar = false
        while (!finalizar) {
            println("\nInformações atuais do funcionário:")
            println(funcionario)

            println("\nEscolha o campo para editar:")
            println("1. Nome")
            println("2. Morada")
            println("3. Contacto")
            println("4. NIF")
            println("5. Email")
            println("6. Finalizar edição")
            print("Opção: ")
            val opcao = readLine()!!

            when (opcao) {
                "1" -> {
                    print("Novo nome: ")
                    funcionario.pessoa.nome = readLine()!!
                }
                "2" -> {
                    print("Nova morada: ")
                    funcionario.pessoa.morada = readLine()!!
                }
                "3" -> {
                    print("Novo contacto: ")
                    funcionario.pessoa.contacto = readLine()!!.toInt()
                }
                "4" -> {
                    print("Novo NIF: ")
                    funcionario.pessoa.nif = readLine()!!.toInt()
                }
                "5" -> {
                    print("Novo email: ")
                    funcionario.pessoa.email = readLine()!!
                }
                "6" -> {
                    finalizar = true
                    println("Funcionário editado com sucesso!")
                }
                else -> println("Opção inválida, tente novamente.")
            }
        }
    } else {
        println("Funcionário não encontrado.")
    }
}

fun removerFuncionario() {
    println("Remover funcionário")
    print("ID do funcionário: ")
    val idFuncionario = readLine()!!.toInt()
    val funcionario = listaFuncionarios.find { it.idFuncionario == idFuncionario }
    if (funcionario != null) {
        listaFuncionarios.remove(funcionario)
        listaPessoas.remove(funcionario.pessoa)
        listaCarros.removeIf { it.dono.idPessoa == funcionario.pessoa.idPessoa }
        println("Funcionário removido com sucesso!")
    } else {
        println("Funcionário não encontrado.")
    }
}
