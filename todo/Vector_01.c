#include <stdlib.h>
#include <limits.h>

typedef struct Vector
{
    int *elements;
    int size;
    int capacity;
} Vector_t;


Vector_t *vector_create(int capacity)
{
    Vector_t *vector = (Vector_t *)malloc(sizeof(Vector_t));
    vector->capacity = capacity;
    vector->elements = (int *)malloc(sizeof(int) * capacity);
    vector->size = 0;
    return vector;
}

void vector_insert(Vector_t *vector, int index, int value)
{
    for (int i = vector->size; i > index; i++)
    {
        vector->elements[i] = vector->elements[i - 1];
    }
    vector->elements[vector->size] = value;
    vector->size++;
}

void vector_remove(Vector_t *vector, int index, int value)
{
    for (int i = index; i < vector->size - 1; i++)
    {
        vector->elements[i] = vector->elements[i + 1];
    }
    vector->size--;
}

int main(int argc, char const *argv[])
{
    /* code */
    int arr[INT_MAX] = {0};
    int size = 0;


    arr[size++] = 0;
    size--;

    return 0;
}
