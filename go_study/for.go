package main

import "fmt"

func main(){
	// x := 1;
	// for x < 5 {
	// 	fmt.Println(x);
	// 	x++;
	// }
	for i := 1 ; i <= 15 ; i++ {
		if i == 3{
			continue;
		} else if i == 8{
			break;
		}
		fmt.Println(i);
	}
}