#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <stdio.h>

typedef struct
{
    char *key;
    char *value;
    bool used;
} HashNode;

typedef struct
{
    HashNode *table;
    int size;
    int capacity;
} HashTable;

HashTable *hashtable_create(int capacity)
{
    HashTable *hashTable = (HashTable *)malloc(sizeof(HashTable));
    hashTable->capacity = capacity;
    hashTable->size = 0;
    hashTable->table = (HashNode *)malloc(sizeof(HashNode) * capacity);
    for (int i = 0; i < capacity; i++) {
        hashTable->table[i].used = false;
        hashTable->table[i].key = "\0";
        hashTable->table[i].value = "\0";
    }
    return hashTable;
}

int hashtable_hashcode(char *key, int capacity)
{
    int hash = 31;
    char *str = key;
    while (*str)
    {
        hash = (hash << 5) - hash + *str++;
    }
    return hash % capacity;
}

int hashtable_detect(HashTable *hashtable, char *key, int flag)
{
    int index = hashtable_hashcode(key, hashtable->capacity);
    for (int i = 0; i < hashtable->capacity; i++)
    {
        if (hashtable->table[index].used == false)
        {
            return flag == 1 ? index : -1;
        }
        else
        {
            if (strcmp(hashtable->table[index].key, key) == 0)
            {
                return index;
            }
            index = (index + 1) % hashtable->capacity;
        }
    }
    return -1;
}

void hashtable_insert(HashTable *hashtable, char *key, char *value)
{
    int index = hashtable_detect(hashtable, key, 1);
    printf("index: %d\n", index);
    if (index != -1)
    {
        hashtable->size++;
        hashtable->table[index].key = key;
        hashtable->table[index].value = value;
        hashtable->table[index].used = true;
    }
}

bool hashtable_contains(HashTable *hashtable, char *key)
{
    int index = hashtable_detect(hashtable, key, 0);
    return index != -1 ? true: false;
}

void hashtable_remove(HashTable *hashtable, char *key)
{
    int index = hashtable_contains(hashtable, key);
    if (index != -1)
    {
        hashtable->size--;
        hashtable->table[index].used = false;
        hashtable->table[index].key = "\0";
        hashtable->table[index].value = "\0";
    }
}

void hashtable_destory(HashTable *hashtable)
{
    free(hashtable->table);
    free(hashtable);
}

int main(int argc, char const *argv[])
{
    HashTable *hashtable = hashtable_create(10);
    hashtable_insert(hashtable, "1", "1");
    hashtable_insert(hashtable, "0", "1");
    hashtable_insert(hashtable, "2", "1");
    hashtable_insert(hashtable, "2123123", "1");
    printf("%d\n", hashtable_contains(hashtable, "11"));
    printf("%d\n", hashtable_contains(hashtable, "0"));

    hashtable_destory(hashtable);
    return 0;
}
