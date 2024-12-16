// Brute Force 暴力破解 
#include <cstring>
#include <cstdio>
#include <vector>

/**
 * s: 待匹配的主串
 * t: 模式串
 * n: 主串的长度
 * m: 模式串的长度
 */
std::vector<int> match(char *s, char *t, int n, int m){
	std::vector<int> ans;
	int i, j;
	for(i = 0; i < n - m + 1; i++) {
		for(j = 0; j < m; j++) {
			if(s[i + j] != t[j]) break;
		}
		if(j == m){
		  ans.push_back(i);
          printf("%d\n", i);
  	    }
	}
	return ans;
}

int main(int argc, char const *argv[])
{
	/* code */
    char s[] = "hello world!";
    char t[] = "world";
    int n = 12;
    int m = 5;
    std::vector<int> ans =  match(s,t,n,m);
	return 0;
}