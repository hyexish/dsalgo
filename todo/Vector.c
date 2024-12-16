#include <stdlib.h>
#include <string.h>
#include <stdio.h>

typedef struct Vector
{
    int *elements;
    int capacity;
    int size;
} Vector_t;

Vector_t *vector_create(int capacity)
{
    Vector_t *vector = (Vector_t *)malloc(sizeof(Vector_t));
    vector->capacity = capacity;
    vector->elements = (int *)malloc(sizeof(int) * capacity);
    memset(vector->elements, 0, sizeof(int) * capacity);
    vector->size = 0;
    return vector;
}

void vector_exspand_capacity(Vector_t *vector)
{
    int newCapacity = vector->capacity + (vector->capacity >> 1);
    int *newElements = (int *)malloc(sizeof(int) * newCapacity);
    memset(newElements, 0, sizeof(int) * newCapacity);
    memcpy(newElements, vector->elements, sizeof(int) * vector->capacity);
    free(vector->elements);
    vector->elements = newElements;
    vector->capacity = newCapacity;
}

void vector_insert(Vector_t *vector, int index, int value)
{
    if (index < 0 || index > vector->size)
    {
        printf("Error: index out of bounds\n");
        return;
    }
    if (vector->size >= vector->capacity)
    {
        vector_exspand_capacity(vector);
    }
    for (int i = vector->size; i > index; i--)
    {
        vector->elements[i] = vector->elements[i - 1];
    }
    vector->elements[index] = value;
    vector->size++;
}

void vector_append(Vector_t *vector, int value)
{
    vector_insert(vector, vector->size, value);
}

void vector_remove(Vector_t *vector, int index)
{
    if (index < 0 || index >= vector->size)
    {
        printf("Error: index out of bounds\n");
        return;
    }
    for (int i = index; i < vector->size - 1; i++)
    {
        vector->elements[i] = vector->elements[i + 1];
    }
    vector->size--;
}

void vector_foreach(Vector_t *vector)
{
    for (int i = 0; i < vector->size; i++)
    {
        printf("%d, ", vector->elements[i]);
    }
    printf("\n");
}

int main(int argc, char const *argv[])
{
    Vector_t *vector = vector_create(3);
    vector_append(vector, 0);
    vector_append(vector, 1);
    vector_append(vector, 2);
    vector_append(vector, 3);
    vector_append(vector, 4);
    vector_remove(vector, vector->size - 1);
    vector_foreach(vector);
    printf("size: %d\n", vector->size);
    return 0;
}
