# include <stdio.h>

int main (void)
{
    int a, b, *p;
    a = 2;
    *p = 3; //  isso esta errado, pois o ponteiro P n foi inicialisado(apontaNdo para algo), 
            //assim pode dar erro como pode funcionar. Pois ele perde a info na memoria,
            //pois um ponteiro fazio (sem apontar para algo) "fica sem registro" na memoria, podendo ficar em qualquer ludar.
    b = a + (*p);
    printf("\n");
    printf("Valor da variavel A: %d \n", a);
    printf("Local da memoria da VAR A: %d \n"), (void*)&a;
    printf("-------------------------------------\n");
    printf("Valor do ponteiro P: %d \n", *p);
    printf("Local da memoria do Ponteiro P: %d \n"), (void*)&p;
    printf("-------------------------------------\n");
    printf("Valor da variavel B: %d \n", b);
    printf("Local da memoria da VAR B: %d \n"), (void*)&b;
    printf("\n");
    return 0; // encerra o metodo MAIN
}

