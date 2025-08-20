# include <stdio.h>

int main()
{
    int num, valor;
    int *p;
    num = 55;
    p = &num;
    valor = *p;
    
    printf("\n Valor de NUM : %d \n ", num);
    printf("Memoria de NUM : %d \n ", (void*)&num);
    printf("------------------ \n");
    
    printf(" Memoria q P esta armazenando(apontando) : %d \n", (void*)p);
    printf(" Valor de *P : %d \n ", *p);
    printf("Memoria de *P : %d \n ", (void*)&p);
    printf("------------------ \n");
    
    printf(" Valor da VAR VALOR: %d \n", valor);
    printf(" Memoria de VALOR: %d \n ", (void*)&valor);
    printf("------------------ \n");
}