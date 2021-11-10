# Chat-Socket-Java
# Version 0.1
Sistema de chat em redes locais (LAN) envolvendo ServerSocket e Socket na linguagem Java.
Ligar primeiro o servidor (Chat.jar) no computador que deseja que o servidor seja iniciado.
Depois em outro computador na mesma rede, abrir o Cliente.jar que vai exibir uma tela de login para que você digite o nome do servidor (seu host).
Ao conectar, troca de mensagens será possível pela mesma rede.

Utilização de cliente TCP/IP da API java.net, utilizando a classe Socket para a criação de um cliente.
Utilização da API java.net, utilizando a classe ServerSocket para a criação de um servidor local naquela máquina.
Utilização de threads, para receber mensagem em paralelo e alterar a interface GUI.

# Próximos passos
Realizar a criptografia das mensagens entre o modelo cliente-servidor.
