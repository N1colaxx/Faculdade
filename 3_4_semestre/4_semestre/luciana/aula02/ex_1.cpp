#include <stdio.h>

int main (void) 
{
    int a;      // Uma var INT 
    int *p;     // Um PONTEIRO, indicado por *, aqui ele n tem nada apontado ainda
    p = &a;     // Aqui armazeno o local da memoria A (o endereço de A). O ponteiro P NÃO guarda o valor, ele guarda o endereço de A.
    *p = 2;     // Aqui add o valor 2 na variável para onde P aponta (no caso, A). Então "a" agora vale 2.

    printf("Valor armazenado em A: %d", a);
    printf("\n");
    
    printf("Endereco de memoria de P: %p", (void*)&p);  // endereço de memória da variável p
    printf("\n");
    
    printf("Endereco de memoria de A: %p", (void*)&a);  // endereço de memória da variável a
    printf("\n");
    
    printf("Valor de memoria armazenado em P (que eh o endereco de A): %p", (void*)p); // valor dentro de p (endereço de A)
    printf("\n");
    
    return 0;
}
