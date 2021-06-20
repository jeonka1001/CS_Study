package main

import "fmt"

// hello := "hello" 이는 불가능

func main(){
	var age = 3;
	switch age{
	case 1:
		fmt.Println("초등생1");
	case 2 :
		fmt.Println("초등생2");
	default:
		fmt.Println("학생 아님");
	}

	name, num := "jeonka",1;
	fmt.Printf("%s, %d\n", name, num);
	name = "jj"
	fmt.Println(name);
	name,num2 := "hello",2;
	fmt.Println(name,num2);
}