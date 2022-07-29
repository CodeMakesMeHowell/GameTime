```json
{
    "Users": {
        "Customers": {
            "username": {
                "name": "",
                "password": "",
                "events": ["{event ids}"]
            }
        },
        "Admins": {
            "username": {
                "name": "",
                "password": "",
                "events": ["{event ids}"]
            }
        }
    },

    "Events": {
        "{venue}_{name}_{startTime}_{endTime}": {
            "name": "",
            "activity": "",
            "venue": "",
            "startTime": "",
            "endTime": "",
            "numRegistered": 0,
            "numPlayers": 420
        }
    },

    "Venues": {
        "{name}": {
            "name": "",
            "activities": ["{sport names}"],
            "events": ["{event ids}"]
        }
    }
}
```