package main

import (
	"fmt"
	"log"
	"sms-store/database"
	"sms-store/kafka"
)

func main() {

	err := database.ConnectDB()
	if err != nil {
		log.Fatal("DB connection failed:", err)
	}

	fmt.Println("MongoDB connected")

	go kafka.StartConsumer()

	select {}
}
