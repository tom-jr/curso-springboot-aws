# AWS

## Calculo de sub-redes IPV4

**Entendendo o Protocolo IPV4**
É formado por 32 bits, dividido en 4 octetos(8 bits cada). Eles são representados por números de 
0 a 255. Além disso o IPV4 precisa de algo chamado de mascara de sub-rede, que trata de redefinir
qual pate do IP representa a rede e qual parte representa pos host daquela rede.

Exemplo

~~~ host
192.168.100.2
255.255.255.0
~~~

No caso acima, temos uma rede que contem 254 IPs utilizáveis, pois a mascara de sub-rede está utilizando 24 bits
para a rede e sobrando com 8 bits dos 32 para os hosts daquela rede. Os bits que determinam a parte da rede tem 
valor 1 e os bits que determinam a parte do host tem valor 0.

O calculo para descobrir quantos hosts podemos ter em uma rede é 2^b -2
Onde b é o numero de bits com valor 0. No caso acima : 2^8 -2 = 254

Mas como eu sei quantas mascas de sub-rede esta utilizando?

**Decimal para binário e binário para decimal**

Tanto para converter decimal para binário e binário para decimal, crie uma tabela dividindo 128 pro 2
ate que chegue ao valor 1

128 64 32 16 8 4 2 1

Dividindo uma mascara 255.255.255.0
temos

\# REDE
11111111 = 255
11111111 = 255
11111111 = 255
\# HOSTs
00000000 = 0


Para nosso exemplo  192.168.100.2, a parte do IP onde a mascara de sub-rede é 1 (192.168.100)
representando uma rede, a parte final(2) representa a identificação de um host da red(any device)

Do numero de disponível são tirados 2 endereços que devem ser utilizados, o primeiro e o ultimo.
O primeiro IP(192.168.100.0) representa o endereço da rede em si, o ultimo endereço (192.168.100.255)
representa o broadcast da rede. Sobrando 254 IPs que podem ser utilizados por quaisquer equipamentos
dentro dessa rede.


***Observação***: Endereços de IP devem ser únicos dentro de uma mesma rede. Nada de dois equipamentos diferentes com o 
mesmo IP.

a mascara 255.255.255.0 é uma das mais usadas localmente devido ter capacidade de suporta IPs ate para uma
pequena empresa.

 **CIDR**

 Além da representação 255.255.255.0, mascaras de sub-reds também podem ser apresentadas no formato CIDR.
 Este método simplesmente indica quantos bits das mascara estão disponíveis para rede

 Exemplo:
 A invés de escrevemos assim
 - Ip: 192.169.100.2 Mascara: 255.255.255.0
 podemos escrever apenas
 - 192.168.100.2/24

 No exemplo, para endereço de rede estamos disponibilizando 24 bits e o restante que é 8
 fica para os hosts das redes


Exemplo mais complexo

 - 192.168.100.2/28

 Bits para rede **1**, bits para hosts: **0**

 - 192.168.100.2/28 <==> 11111111.11111111.11111111.11110000

Onde serão 28 bits para rede e 4 bits para hosts

e para calcular o numero de hosts 2^4 - 2 = 14 IPs


