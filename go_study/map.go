package main

import "fmt"

func main() {
	var zoo map[string]int = map[string]int{
		"코끼리": 3,
		"사자":  7,
	}
	zoo["코알라"] = 90
	fmt.Println(zoo)
	fmt.Println(zoo["사자"])
	delete(zoo, "강아지")
	val, okay := zoo["고양이"]
	fmt.Println(val, okay)
	fmt.Println(zoo)
}
