package kafka

import (
	"context"
	"encoding/json"
	"fmt"
	"log"

	"github.com/segmentio/kafka-go"
	"sms-store/models"
	"sms-store/repository"
)

func StartConsumer() {

	reader := kafka.NewReader(kafka.ReaderConfig{
		Brokers: []string{"localhost:9092"},
		Topic:   "sms-topic",
		GroupID: "sms-store-group",
	})

	fmt.Println("Kafka consumer started...")

	for {

		msg, err := reader.ReadMessage(context.Background())
		if err != nil {
			log.Println("Kafka read error:", err)
			continue
		}

		var sms models.SMS

		err = json.Unmarshal(msg.Value, &sms)
		if err != nil {
			log.Println("JSON error:", err)
			continue
		}

		fmt.Println("Received SMS:", sms)

		err = repository.SaveSMS(sms)
		if err != nil {
			log.Println("Mongo save error:", err)
		} else {
			fmt.Println("SMS saved to MongoDB")
		}
	}
}
