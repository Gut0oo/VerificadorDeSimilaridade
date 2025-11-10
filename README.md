# Verificador de Similaridade de Textos com Hash e AVL
---

## 🔹Descrição
Este projeto propõe o desenvolvimento de um sistema robusto e eficiente para identificar o grau de similaridade entre documentos textuais. A tarefa de detecção de similaridade é crucial em diversas aplicações de ciência da computação, como a detecção de plágio acadêmico

## 🔹Funcionalidades

-  Exibir todos os pares com similaridade acima de um limiar informado pelo usuário;
-  Exibir apenas os K pares mais semelhantes, sendo o K informado pelo usuário;
-  Comparar dois arquivos específicos, informados pelo usuário.


## 🔹Ferramentas

- Linguagem Java
- VisualStudio code


## 🔹Execução

- Trocar o diretório atual para o diretório ***src***
~~~
cd src
~~~

- Linha de comando para o comando ***Lista***
~~~
java -cp . Main.App <diretorio_documentos> <limiar> lista
~~~

- Linha de comando para o comando ***topK***
~~~
java -cp . Main.App <diretorio_documentos> <limiar> topK <K>
~~~

- Linha de comando para o comando ***busca***
~~~
java -cp . Main.App <diretorio_documentos> 0.0 busca <docX.txt> <docX.txt>
~~~

