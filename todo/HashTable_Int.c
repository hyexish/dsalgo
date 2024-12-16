#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>

#define FLAG_READ 0
#define FLAG_WRITE 1

#define STATUS_UNUSED  0
#define STATUS_USED  1

typedef struct HashNode_s
{
    int key;
    int value;
    bool used;
} HashNode;

typedef struct HashTable_s
{
    int capacity;
    int size;
    HashNode *table;
} HashTable;

int hashtable_detect(HashTable *hashtable, int key, int flag)
{
    int code = key % hashtable->capacity;
    int index = code;
    for (int offset = 1; offset < hashtable->capacity; offset++)
    {
        if (!hashtable->table[index].used)
            return flag ? index : -1;
        else
        {
            if (hashtable->table[index].key == key)
                return index;
            index = (code + offset) % hashtable->capacity;
        }
    }
    return -1;
}

HashTable *hashtable_create(int capacity)
{
    HashTable *hashtable = (HashTable *)malloc(sizeof(HashTable));
    hashtable->capacity = capacity;
    hashtable->size = 0;
    hashtable->table = (HashNode *)malloc(sizeof(HashNode) * capacity);
    for (int i = 0; i < capacity; i++)
    {
        hashtable->table[i].used = false;
    }
    return hashtable;
}

int hashtable_insert(HashTable *hashtable, int key, int value)
{
    int index = hashtable_detect(hashtable, key, FLAG_WRITE);
    if (index != -1)
    {
        hashtable->table[index].key = key;
        hashtable->table[index].value = value;
        hashtable->table[index].used = true;
        hashtable->size++;
    }
    return hashtable->size;
}

int hashtable_contains(HashTable *hashtable, int key)
{
    return hashtable_detect(hashtable, key, FLAG_READ);
}

int hashtable_remove(HashTable *hashtable, int key)
{
    int index = hashtable_contains(hashtable, key);
    if (index != -1)
    {
        hashtable->table[index].used = false;
        hashtable->size--;
    }
    return index;
}

int hashtable_search_value(HashTable *hashtable, int key)
{
    int index = hashtable_contains(hashtable, key);
    if (index != -1)
    {
        return hashtable->table[index].value;
    }
    return -1;
}

void hashtable_print(HashTable *hashtable)
{
    for (int i = 0; i < hashtable->capacity; i++)
    {
        if (hashtable->table[i].used)
        {
            printf("index: %d, key: %d, value: %d\n", i, hashtable->table[i].key, hashtable->table[i].value);
        }
    }
    printf("\n");
}

int main()
{
    HashTable *hashtable = hashtable_create(10);
    hashtable_insert(hashtable, 0, 0);
    hashtable_insert(hashtable, 1, 1);
    hashtable_insert(hashtable, 5, 5);
    hashtable_insert(hashtable, 15, 5);
    hashtable_insert(hashtable, 25, 5);

    printf("size: %d\n", hashtable->size);
    printf("contains: %d\n", hashtable_contains(hashtable, 9));

    hashtable_print(hashtable);

    return 0;
}