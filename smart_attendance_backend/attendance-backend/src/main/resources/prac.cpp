#include <bits/stdc++.h>

using namespace std;  

void solve(int n){
    vector<int>s;

    for(int i = 2 ; i < n ; i++){
        int flag = false ;
        for(auto p : s)if(i % p ==0)flag = true ;
        
        if(!flag)s.push_back(i) ;
    }
    for(auto i : s)cout<<i<<" "<<endl;
    return ;
}

int main(){
    int n ; cin>>n ;

    solve(n) ;
}