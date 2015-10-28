# rm
rm slot booking application

URL : POST http://rocky-wildwood-8040.herokuapp.com/slotavailability

Sample request


{
    "meta": {
        "vans": {
            "num": 1,
            "maxCapacity": 6750,
            "operatingHours": {
            	"from" : 540,
            	"to" : 1080
            }
        },
        "products": [
            {
                "id": "p1",
                "description": "Product 1",
                "amount": 100,
                "dimension": 200
            },
            {
                "id": "p2",
                "description": "Product 2",
                "amount": 150,
                "dimension": 2000
            },
            {
                "id": "p3",
                "description": "Product 3",
                "amount": 7000,
                "dimension": 4500
            }
        ],
        "customers": [
            {
                "id": 1,
                "name": "cust 1",
                "phone": 9999999999,
                "Address": {
                    "line1": "#123, 15th cross, 5th main",
                    "line2": "HSR layout",
                    "city": "bengaluru",
                    "country": "in",
                    "zipcode": 560102
                }
            },
            {
                "id": 2,
                "name": "cust 2",
                "phone": 8888888888,
                "Address": {
                    "line1": "#345, 10th cross, 15th main",
                    "line2": "BTM layout",
                    "city": "bengaluru",
                    "country": "in",
                    "zipcode": 560076
                }
            },
            {
                "id": 3,
                "name": "cust 3",
                "phone": 6666666666,
                "Address": {
                    "line1": "#344, 10th cross, 15th main",
                    "line2": "BTM layout",
                    "city": "bengaluru",
                    "country": "in",
                    "zipcode": 560076
                }
            }
        ]
    },
    "order": [{
        "id": "1",
        "products": [
            "p1",
            "p2",
            "p3"
        ],
        "customer": {
            "id": 1
        },
        "slot": {
            "date": "",
            "from": 540,
            "to": 660
        }
    },
    {
        "id": "2",
        "products": [
            "p1",
            "p2"
        ],
        "customer": {
            "id": 2
        },
        "slot": {
            "date": "",
            "from": 540,
            "to": 660
        }
    },
 {
        "id": "3",
        "products": [
            "p3"
        ],
        "customer": {
            "id": 2
        },
        "slot": {
            "date": 1445858459337,
            "from": 540,
            "to": 660
        }
    }
]
}

Notes/Improvements:
- sample request contains 'meta' section and 'order' section. 'meta' section should be kept as it is. Application may give unexpected result as the input request is modified greatly 
- Slot booking application does not use database. Instead it relies on the meta information passed in the API request
- Unit test not written
- API request/response documentation not done
- For MongoDB, Morphia POJO Wrapper is used. Could have created DAOs instead of putting control logic in controller
