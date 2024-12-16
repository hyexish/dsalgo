#include <stdlib.h>
#include <stdio.h>
#include <string.h>

typedef struct HashNode_s
{
    char *key;
    char *value;
    int status;
} HashNode;

typedef struct HashTable_s
{
    int capacity;
    int size;
    HashNode *table;
} HashTable;

int hashCode(char *key, int capacity)
{
    unsigned long hash = 0;
    while (*key)
    {
        hash = (hash * 31 + *key++) % capacity;
    }
    return hash % capacity;
}

int openAddress(int code, int offset, int capacity)
{
    return (code + offset) % capacity;
}

int detectAddrees(HashTable *hashtable, int code, int index, int key, int value)
{
    int index = code;
    for (int offset = 1; offset < hashtable->capacity; offset++)
    {
        if (hashtable->table[index].status == 0)
        {
            return index;
        }
        else
        {
            if (hashtable->table[index].key == key)
            {
                return index;
            }
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
        hashtable->table[i].status = 0;
    }
    return hashtable;
}

int hashtable_insert(HashTable *hashtable, char *key, char *value)
{
    int code = hashCode(key, hashtable->capacity);
    int index = code;
    for (int offset = 1; offset < hashtable->capacity; offset++)
    {
        if (hashtable->table[index].status == 0)
        {
            hashtable->table[index].value = (char *)malloc(sizeof(char) * strlen(value));
            if (strcmp(hashtable->table[index].key, key) == 0)
            {
                hashtable->table[index].key = key;
                hashtable->table[index].status = 1;
                hashtable->size++;
            }
            return index;
        }
        index = openAddress(code, offset, hashtable->capacity);
    }
    return -1;
}

int hashtable_contains(HashTable *hashtable, int key)
{
    int code = hashCode(key, hashtable->capacity);
    for (int i = 1; i < hashtable->capacity; i++)
    {
        if (hashtable->table[index].status == 0)
            return -1;
        if (hashtable->table[index].status == 1 && hashtable->table[index].key == key)
            return index;
        index = openAddress(code, i, hashtable->capacity);
    }
    return -1;
}

void hashtable_remove(HashTable *hashtable, int key)
{
    int index = hashtable_contains(hashtable, key);
    if (index == -1)
        return;
    hashtable->table[index].status = 0;
    hashtable->size--;
}

int hashtable_search_value(HashTable *hashtable, int key)
{
    int index = hashtable_contains(hashtable, key);
    if (index == -1)
        return -1;
    return hashtable->table[index].value;
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

    for (int i = 0; i < hashtable->capacity; i++)
    {
        if (hashtable->table[i].status == 1)
        {
            printf("index: %d, key: %d, value: %d\n", i, hashtable->table[i].key, hashtable->table[i].value);
        }
    }
    return 0;
}