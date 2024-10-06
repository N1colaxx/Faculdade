
from Crypto.Cipher import AES
from Crypto.Random import get_random_byte


key = get_random_byte(32)
iv = get_random_byte(16)


# Criptografar
cipher = AES.new(key, AES.MODE_CFB, iv)
cleanText = b"Ola sou NICo"
cript_Text = cipher.encrypt(cleanText.ljust(16)) # ljust em crypto é para add 0 ao bloco caso ele n seja do tamanho ideal 


# des Cripto

cipher_Desc = AES.new(key, AES.MODE_CFB, iv)
descc_Text = cipher_Desc.decrypt(cript_Text)









