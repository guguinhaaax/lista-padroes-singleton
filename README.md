# lista-padroes-singleton

Resposta da primeira questão da Lista Avaliativa II da disciplina Padrões de Objeto, relativo ao padrão Singleton.

-------------------------------------------------------------------------------------------------------------------------

Vários tipos de software mantêm arquivos de configuração/propriedades
centralizados por conta do benefício de consistência e desempenho. Isso evita que
vários usuários acessem e leiam repetidamente o arquivo. O software cria uma única
instância do arquivo de configuração que pode ser acessada por várias chamadas
simultaneamente, pois fornecerá dados de configuração estáticos carregados em
objetos na memória. O software apenas lê o arquivo de configuração na primeira vez
e, a partir da segunda chamada, os aplicativos clientes leem os dados dos objetos na
memória.

Dado o exemplo de arquivo de configuração de um jogo abaixo, usando o Padrão
Singleton, escreva um programa que lê o arquivo, o armazena em memória, e imprime
todos os dados do arquivo de configuração. Você deverá armazenar os dados do
arquivo numa classe que modela/representa o arquivo de configuração a ser lido.
Note que outros componentes do software que usam o arquivo de configuração
podem querer acessar individualmente os campos do arquivo de configuração.

    AppId=1649240
    UserName=elamigos
    Language=brazilian
    Offline=0
    AutoDLC=0
    BuildId=10547147
    DLCName=Returnal Pre-Purchase Entitlement
    UpdateDB=1
    Signature=FitGirl
    WindowInfo=LAAAAAAAAAAIP50AAACIAAAATQMAAJACAAA=
    LVWindowInfo=AwAAADEBAAByAAAApQAAAA==
    ApplicationPath=../../../Returnal.exe
    WorkingDirectory=
    WaitForExit=1
    NoOperation=0
