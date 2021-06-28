package main

import "fmt"

type Duck struct{}

func (d Duck) sound() {
	fmt.Println("꽥꽥")
}
func (d Duck) taste() {
	fmt.Println("오리 맛있다")
}

type Fish struct{}

func (f Fish) sound() {
	fmt.Println("푸다닥")
}
func (f Fish) taste() {
	fmt.Println("물고기 맛있다")
}

type hunting interface {
	sound()
	taste()
}

func inTheRiver(h hunting) {
	h.sound()
	h.taste()
}

func main() {
	var Mcdonald Duck
	var Marine Fish
	inTheRiver(Mcdonald)
	inTheRiver(Marine)
}

// type 인터페이스명 interface { 메서드 반환형 }
