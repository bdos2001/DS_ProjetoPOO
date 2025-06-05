fun menuClientes() {
    var finalizar = false
    while (!finalizar) {
        println("=====================================")
        println("Área dos Clientes")
        println("=====================================")
        println("1. Adicionar cliente")
        println("2. Listar clientes")
        println("3. Editar cliente")
        println("4. Remover cliente")
        println("5. Mostrar carros do cliente")
        println("0. Voltar ao menu anterior")
        println("=====================================")
        print("Escolha uma opção: ")

        val opcao = readLine()!!.toInt()
        cls()
        when (opcao) {
            1 -> adicionarCliente()
            2 -> listarClientes()
            3 -> editarCliente()
            4 -> removerCliente()
            5 -> mostrarCarrosCliente()
            0 -> finalizar = true
            else -> println("Opção inválida, tente novamente.")
        }
    }
}

fun adicionarCliente() {
    println("Adicionar cliente")

    // Perguntar se o cliente já é funcionário
    println("O cliente já é funcionário? (S/N)")
    val resposta = readLine()!!.uppercase()

    val pessoa: Pessoa

    if (resposta == "S") {
        // Se for funcionário, pedir o ID do funcionário
        print("ID do funcionário: ")
        val idFuncionario = readLine()!!.toInt()
        val funcionario = listaFuncionarios.find { it.idFuncionario == idFuncionario }

        if (funcionario != null) {
            // Usar a pessoa associada ao funcionário
            pessoa = funcionario.pessoa
            println("Usando dados do funcionário existente: ${pessoa.nome}")

            // Verificar se já existe um cliente com o ID da Pessoa desse funcionário
            val clienteExistente = listaClientes.find { it.pessoa.idPessoa == pessoa.idPessoa }
            if (clienteExistente != null) {
                println("ATENÇÃO: Já existe um cliente com o ID de Pessoa ${pessoa.idPessoa}.")
                println("Cliente existente: ID ${clienteExistente.idCliente}, Nome: ${clienteExistente.pessoa.nome}")
                println("Operação cancelada.")
                return
            }
        } else {
            println("Funcionário não encontrado. Criando novo cliente com novos dados.")
            // Criar nova pessoa
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

            val idPessoa = if (listaPessoas.isEmpty()) 1 else listaPessoas.maxOf { it.idPessoa } + 1
            pessoa = Pessoa(idPessoa, nome, morada, contacto, nif, email)
            listaPessoas.add(pessoa)
        }
    } else {
        // Criar nova pessoa
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

        val idPessoa = if (listaPessoas.isEmpty()) 1 else listaPessoas.maxOf { it.idPessoa } + 1
        pessoa = Pessoa(idPessoa, nome, morada, contacto, nif, email)
        listaPessoas.add(pessoa)
    }

    // Criar novo cliente com a pessoa (existente ou nova)
    val idCliente = if (listaClientes.isEmpty()) 1 else listaClientes.maxOf { it.idCliente } + 1
    val cliente = Cliente(idCliente, pessoa)
    listaClientes.add(cliente)

    println("Cliente adicionado com sucesso!")
}

fun listarClientes() {
    println("Listar clientes")
    for (cliente in listaClientes) {
        println(cliente)
    }
}

fun editarCliente() {
    println("Editar cliente")
    print("ID do cliente: ")
    val idCliente = readLine()!!.toInt()
    val cliente = listaClientes.find { it.idCliente == idCliente }
    if (cliente != null) {
        var finalizar = false
        while (!finalizar) {
            println("\nInformações atuais do cliente:")
            println(cliente)

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
                    cliente.pessoa.nome = readLine()!!
                }
                "2" -> {
                    print("Nova morada: ")
                    cliente.pessoa.morada = readLine()!!
                }
                "3" -> {
                    print("Novo contacto: ")
                    cliente.pessoa.contacto = readLine()!!.toInt()
                }
                "4" -> {
                    print("Novo NIF: ")
                    cliente.pessoa.nif = readLine()!!.toInt()
                }
                "5" -> {
                    print("Novo email: ")
                    cliente.pessoa.email = readLine()!!
                }
                "6" -> {
                    finalizar = true
                    println("Cliente editado com sucesso!")
                }
                else -> println("Opção inválida, tente novamente.")
            }
        }
    } else {
        println("Cliente não encontrado.")
    }
}

fun mostrarCarrosCliente() {
    println("Mostrar carros do cliente")
    print("ID do cliente: ")
    val idCliente = readLine()!!.toInt()
    val cliente = listaPessoas.find { it.idPessoa == idCliente }
    if (cliente != null) {
        println("Carros do cliente ${cliente.nome}:")
        for (carro in listaCarros) {
            if (carro.dono.idPessoa == idCliente) {
                println(carro)
            }
        }
    } else {
        println("Cliente não encontrado.")
    }
}

fun removerCliente() {
    println("Remover cliente")
    print("ID do cliente: ")
    val idCliente = readLine()!!.toInt()
    val cliente = listaClientes.find { it.idCliente == idCliente }
    if (cliente != null) {
        listaClientes.remove(cliente)

        // Verifica se a pessoa também é um funcionário antes de removê-la
        val pessoaEhFuncionario = listaFuncionarios.any { it.pessoa.idPessoa == cliente.pessoa.idPessoa }
        if (!pessoaEhFuncionario) {
            // Se a pessoa não for funcionário, remove-a da lista de pessoas
            listaPessoas.remove(cliente.pessoa)
        }

        listaCarros.removeIf { it.dono.idPessoa == cliente.pessoa.idPessoa }
        println("Cliente removido com sucesso!")
    } else {
        println("Cliente não encontrado.")
    }
}
