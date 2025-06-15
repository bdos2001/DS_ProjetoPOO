fun menuAreaCarros() {
    var finalizar = false
    while (!finalizar) {
        println("=====================================")
        println("Área de Carros")
        println("=====================================")
        println("1. Adicionar carro")
        println("2. Listar carros")
        println("3. Editar carro")
        println("4. Remover carro")
        println("5. Marcas/Modelos")
        println("0. Sair")
        println("=====================================")
        print("Escolha uma opção: ")
        try {
            val opcao = readLine()!!.toInt()
            cls()
            when (opcao) {
                1 -> adicionarCarro()
                2 -> listarCarros()
                3 -> editarCarro()
                4 -> removerCarro()
                5 -> menuMarcasModelos()
                0 -> finalizar = true
                else -> println("Opção inválida, tente novamente.")
            }
        } catch (e: NumberFormatException) {
            cls()
            println("Erro: Por favor, insira um número válido.")
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
        listarMarcas()
        print("ID da Marca: ")
        try {
            val idMarca = readLine()!!.toInt()
            marca = listaMarcas.find { it.id == idMarca }
            if (marca == null) {
                println("Marca não encontrada. Tente novamente.")
            }
        } catch (e: NumberFormatException) {
            println("Erro: Por favor, insira um número válido para o ID da marca.")
        }
    }

    var modelo: Modelo? = null
    while (modelo == null) {
        println("Escolha o Modelo associado à Marca '${marca.nome}' pelo ID:")
        listaModelos.filter { it.marca.id == marca.id }.forEach { println(it) }
        print("ID do Modelo: ")
        try {
            val idModelo = readLine()!!.toInt()
            modelo = listaModelos.find { it.id == idModelo && it.marca.id == marca.id }
            if (modelo == null) {
                println("Modelo não encontrado.")
            }
        } catch (e: NumberFormatException) {
            println("Erro: Por favor, insira um número válido para o ID do modelo.")
        }
    }

    var ano: Int? = null
    while (ano == null) {
        print("Ano do Carro: ")
        try {
            ano = readLine()!!.toInt()
        } catch (e: NumberFormatException) {
            println("Erro: Por favor, insira um número válido para o ano.")
        }
    }

    var dono: Pessoa? = null
    while (dono == null) {
        println("Escolha o Dono pelo ID:")
        listarPessoas()
        print("ID do Dono: ")
        try {
            val idDono = readLine()!!.toInt()
            dono = listaPessoas.find { it.idPessoa == idDono }
            if (dono == null) {
                println("Dono não encontrado.")
            }
        } catch (e: NumberFormatException) {
            println("Erro: Por favor, insira um número válido para o ID do dono.")
        }
    }

    val carro = Carro(matricula, modelo, ano, dono)
    listaCarros.add(carro)
    println("Carro adicionado com sucesso!")
}

fun listarCarros() {
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

    var carro: Carro? = null
    try {
        val opcaoBusca = readLine()!!.toInt()

        carro = when (opcaoBusca) {
            1 -> {
                print("Matrícula do Carro: ")
                val matricula = readLine()!!
                listaCarros.find { it.matricula == matricula }
            }
            2 -> {
                println("Escolha o Dono pelo ID:")
                listarPessoas()
                print("ID do Dono: ")
                try {
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
                } catch (e: NumberFormatException) {
                    println("Erro: Por favor, insira um número válido para o ID do dono.")
                    null
                }
            }
            else -> {
                println("Opção inválida.")
                null
            }
        }
    } catch (e: NumberFormatException) {
        println("Erro: Por favor, insira um número válido para a opção.")
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
            try {
                val opcao = readLine()!!.toInt()
                when (opcao) {
                    1 -> {
                        print("Nova Matrícula: ")
                        carro.matricula = readLine()!!
                        println("Edição concluída: $carro")
                    }
                    2 -> {
                        println("Escolha a marca pelo ID:")
                        listarMarcas()
                        print("ID da Marca: ")
                        try {
                            val idMarca = readLine()!!.toInt()
                            val marca = listaMarcas.find { it.id == idMarca }
                            if (marca != null) {
                                println("Escolha o Modelo associado à Marca '${marca.nome}' pelo ID:")
                                listaModelos.filter { it.marca.id == marca.id }.forEach { println(it) }
                                print("ID do Modelo: ")
                                try {
                                    val idModelo = readLine()!!.toInt()
                                    val modelo = listaModelos.find { it.id == idModelo && it.marca.id == marca.id }
                                    if (modelo != null) {
                                        carro.modelo = modelo
                                        println("Edição concluída: $carro")
                                    } else {
                                        println("Modelo não encontrado.")
                                    }
                                } catch (e: NumberFormatException) {
                                    println("Erro: Por favor, insira um número válido para o ID do modelo.")
                                }
                            } else {
                                println("Marca não encontrada.")
                            }
                        } catch (e: NumberFormatException) {
                            println("Erro: Por favor, insira um número válido para o ID da marca.")
                        }
                    }
                    3 -> {
                        print("Novo Ano: ")
                        try {
                            carro.ano = readLine()!!.toInt()
                            println("Edição concluída: $carro")
                        } catch (e: NumberFormatException) {
                            println("Erro: Por favor, insira um número válido para o ano.")
                        }
                    }
                    4 -> {
                        println("Escolha o Dono pelo ID:")
                        listarPessoas()
                        print("ID do Dono: ")
                        try {
                            val idDono = readLine()!!.toInt()
                            val dono = listaPessoas.find { it.idPessoa == idDono }
                            if (dono != null) {
                                carro.dono = dono
                                println("Edição concluída: $carro")
                            } else {
                                println("Dono não encontrado.")
                            }
                        } catch (e: NumberFormatException) {
                            println("Erro: Por favor, insira um número válido para o ID do dono.")
                        }
                    }
                    0 -> continuar = false
                    else -> println("Opção inválida.")
                }
            } catch (e: NumberFormatException) {
                println("Erro: Por favor, insira um número válido para a opção.")
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

    var carro: Carro? = null
    try {
        val opcaoBusca = readLine()!!.toInt()

        carro = when (opcaoBusca) {
            1 -> {
                print("Matrícula do Carro: ")
                val matricula = readLine()!!
                listaCarros.find { it.matricula == matricula }
            }
            2 -> {
                println("Escolha o Dono pelo ID:")
                listarPessoas()
                print("ID do Dono: ")
                try {
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
                } catch (e: NumberFormatException) {
                    println("Erro: Por favor, insira um número válido para o ID do dono.")
                    null
                }
            }
            else -> {
                println("Opção inválida.")
                null
            }
        }
    } catch (e: NumberFormatException) {
        println("Erro: Por favor, insira um número válido para a opção.")
    }

    if (carro != null) {
        listaCarros.remove(carro)
        println("Carro removido com sucesso!")
    } else {
        println("Carro não encontrado.")
    }
}
