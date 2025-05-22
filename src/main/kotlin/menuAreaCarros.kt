fun menuAreaCarros() {
    var finalizar = false
    while (!finalizar) {
        println("Área de Carros")
        println("1. Adicionar carro")
        println("2. Mostrar carros")
        println("3. Editar carro")
        println("4. Remover carro")
        println("5. Marcas/Modelos")
        println("0. Sair")
        print("Escolha uma opção: ")
        val opcao = readLine()!!.toInt()
        cls()
        when (opcao) {
            1 -> adicionarCarro()
            2 -> mostrarCarros()
            3 -> editarCarro()
            4 -> removerCarro()
            5 -> menuMarcasModelos()
            0 -> finalizar = true
            else -> println("Opção inválida, tente novamente.")
        }
    }
}

fun adicionarCarro() {
    println("Adicionar Carro")
    print("Matrícula do Carro: ")
    val matricula = readLine()!!
    if (listaCarros.any { it.matricula == matricula }) {
        println("Matrícula já existe. Tente novamente.")
        return
    }

    var marca: Marca? = null
    while (marca == null) {
        println("Escolha a Marca pelo ID:")
        mostrarMarcas()
        print("ID da Marca: ")
        val idMarca = readLine()!!.toInt()
        marca = listaMarcas.find { it.id == idMarca }
        if (marca == null) {
            println("Marca não encontrada. Tente novamente.")
        }
    }

    var modelo: Modelo? = null
    while (modelo == null) {
        println("Escolha o Modelo associado à Marca '${marca.nome}' pelo ID:")
        listaModelos.filter { it.marca.id == marca.id }.forEach { println(it) }
        print("ID do Modelo: ")
        val idModelo = readLine()!!.toInt()
        modelo = listaModelos.find { it.id == idModelo && it.marca.id == marca.id }
        if (modelo == null) {
            println("Modelo não encontrado. Tente novamente.")
        }
    }

    print("Ano do Carro: ")
    val ano = readLine()!!.toInt()

    var dono: Pessoa? = null
    while (dono == null) {
        println("Escolha o Dono pelo ID:")
        mostrarPessoas()
        print("ID do Dono: ")
        val idDono = readLine()!!.toInt()
        dono = listaPessoas.find { it.idPessoa == idDono }
        if (dono == null) {
            println("Dono não encontrado. Tente novamente.")
        }
    }

    val carro = Carro(matricula, modelo, ano, dono)
    listaCarros.add(carro)
    println("Carro adicionado com sucesso!")
}

fun mostrarCarros() {
    println("Listar Carros")
    for (carro in listaCarros) {
        println(carro)
    }
}

fun editarCarro() {
    println("Editar Carro")
    println("Deseja procurar o carro por:")
    println("1. Matrícula")
    println("2. Dono")
    print("Escolha uma opção: ")
    val opcaoBusca = readLine()!!.toInt()

    val carro: Carro? = when (opcaoBusca) {
        1 -> {
            print("Matrícula do Carro: ")
            val matricula = readLine()!!
            listaCarros.find { it.matricula == matricula }
        }
        2 -> {
            println("Escolha o Dono pelo ID:")
            mostrarPessoas()
            print("ID do Dono: ")
            val idDono = readLine()!!.toInt()
            val dono = listaPessoas.find { it.idPessoa == idDono }
            if (dono != null) {
                println("Carros do Dono '${dono.nome}':")
                listaCarros.filter { it.dono.idPessoa == dono.idPessoa }.forEach { println(it) }
                print("Matrícula do Carro: ")
                val matricula = readLine()!!
                listaCarros.find { it.matricula == matricula && it.dono.idPessoa == dono.idPessoa }
            } else {
                println("Dono não encontrado.")
                null
            }
        }
        else -> {
            println("Opção inválida.")
            null
        }
    }

    if (carro != null) {
        println("Carro encontrado: $carro")
        var continuar = true
        while (continuar) {
            println("O que deseja editar?")
            println("1. Matrícula")
            println("2. Modelo")
            println("3. Ano")
            println("4. Dono")
            println("0. Sair do menu de edição")
            print("Escolha uma opção: ")
            val opcao = readLine()!!.toInt()
            when (opcao) {
                1 -> {
                    print("Nova Matrícula: ")
                    carro.matricula = readLine()!!
                    println("Edição concluída: $carro")
                }
                2 -> {
                    println("Escolha a marca pelo ID:")
                    mostrarMarcas()
                    print("ID da Marca: ")
                    val idMarca = readLine()!!.toInt()
                    val marca = listaMarcas.find { it.id == idMarca }
                    if (marca != null) {
                        println("Escolha o Modelo associado à Marca '${marca.nome}' pelo ID:")
                        listaModelos.filter { it.marca.id == marca.id }.forEach { println(it) }
                        print("ID do Modelo: ")
                        val idModelo = readLine()!!.toInt()
                        val modelo = listaModelos.find { it.id == idModelo && it.marca.id == marca.id }
                        if (modelo != null) {
                            carro.modelo = modelo
                            println("Edição concluída: $carro")
                        } else {
                            println("Modelo não encontrado.")
                        }
                    } else {
                        println("Marca não encontrada.")
                    }
                }
                3 -> {
                    print("Novo Ano: ")
                    carro.ano = readLine()!!.toInt()
                    println("Edição concluída: $carro")
                }
                4 -> {
                    println("Escolha o Dono pelo ID:")
                    mostrarPessoas()
                    print("ID do Dono: ")
                    val idDono = readLine()!!.toInt()
                    val dono = listaPessoas.find { it.idPessoa == idDono }
                    if (dono != null) {
                        carro.dono = dono
                        println("Edição concluída: $carro")
                    } else {
                        println("Dono não encontrado.")
                    }
                }
                0 -> continuar = false
                else -> println("Opção inválida.")
            }
        }
    } else {
        println("Carro não encontrado.")
    }
}

fun removerCarro() {
    println("Remover Carro")
    println("Deseja procurar o carro por:")
    println("1. Matrícula")
    println("2. Dono")
    print("Escolha uma opção: ")
    val opcaoBusca = readLine()!!.toInt()

    val carro: Carro? = when (opcaoBusca) {
        1 -> {
            print("Matrícula do Carro: ")
            val matricula = readLine()!!
            listaCarros.find { it.matricula == matricula }
        }
        2 -> {
            println("Escolha o Dono pelo ID:")
            mostrarPessoas()
            print("ID do Dono: ")
            val idDono = readLine()!!.toInt()
            val dono = listaPessoas.find { it.idPessoa == idDono }
            if (dono != null) {
                println("Carros do Dono '${dono.nome}':")
                listaCarros.filter { it.dono.idPessoa == dono.idPessoa }.forEach { println(it) }
                print("Matrícula do Carro: ")
                val matricula = readLine()!!
                listaCarros.find { it.matricula == matricula && it.dono.idPessoa == dono.idPessoa }
            } else {
                println("Dono não encontrado.")
                null
            }
        }
        else -> {
            println("Opção inválida.")
            null
        }
    }

    if (carro != null) {
        listaCarros.remove(carro)
        println("Carro removido com sucesso!")
    } else {
        println("Carro não encontrado.")
    }
}