# Getting Beacons BLE
Getting Beacons BLE is a simple yet powerful tool for logging Bluetooth beacon data and sending it to a specified server. This application captures the MAC address, distance, RSSI, and TX power of detected beacons and sends the information to a designated server endpoint.

## Features

- **Beacon Detection**: Automatically scans and lists nearby Bluetooth beacons.
- **Customizable Server Endpoint**: Allows you to specify the IP address and port of your server.
- **Automated Data Logging**: Sends beacon information to the server's `/log` endpoint in a structured JSON format.

## Usage

1. **Enter Server IP and Port**: Input the server's IP address and port number in the provided text field.
2. **Beacon List**: The app will display a list of detected beacons, including their MAC addresses and calculated distances.

## Data Format

The app sends the collected beacon data to the server in the following JSON format:

```json
{
  "logBeacons": [
    {
      "distance": number,
      "macBeacon": string,
      "rssi": number,
      "txPower": number
    }
  ]
}
```

## Example Payload
```json
{
  "logBeacons": [
    {
      "distance": 3.74,
      "macBeacon": "55:EF:47:9F:7E:00",
      "rssi": -70,
      "txPower": -59
    },
    {
      "distance": 1.23,
      "macBeacon": "61:96:91:72:EA:3D",
      "rssi": -60,
      "txPower": -59
    }
  ]
}
```

## Installation

1. Clone the repository:

    ```sh
    git clone https://github.com/rlindoso/getting_beacons_ble.git
    ```

2. Open the project in your preferred development environment.
3. Build and run the application on your device.

## Contributing

1. Fork the repository.
2. Create your feature branch:

    ```sh
    git checkout -b feature/your-feature-name
    ```

3. Commit your changes:

    ```sh
    git commit -m 'Add some feature'
    ```

4. Push to the branch:

    ```sh
    git push origin feature/your-feature-name
    ```

5. Open a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
