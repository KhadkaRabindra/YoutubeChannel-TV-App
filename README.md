# YoutubeChannel-TV-App

A sample Android app that demonstrates the use of the YouTube Data API v3. This app allows you to convert your YouTube channel into a native Android app, fetching the latest videos and listing all the playlists from your channel. The app provides an easy way to display your content in a mobile format.

## Features 📱

* Latest Videos: Displays the most recent videos from your YouTube channel.
* Channel Playlists: Lists all playlists from your YouTube channel.
* Play Videos: Users can view the number of videos in each playlist and play videos directly in the app.

## Installation 📲

To get started with the YoutubeChannel-TV-App, follow these steps:

1. Clone the repository:
git clone https://github.com/KhadkaRabindra/YoutubeChannel-TV-App
2. Open the project in Android Studio:
Open the project in Android Studio by selecting Open an existing project and navigating to the cloned project directory.

3. Add YouTube Data API Key:
To fetch data from YouTube, you need a valid YouTube Data API v3 key. Follow these steps:

* Visit Google Developers Console
* Create a new project or select an existing one.
* Enable the YouTube Data API v3 for your project.
* Generate an API key and copy it.
4. Update API Key:
Open build.gradle (Module: app) and add your API key in the defaultConfig section:

```
defaultConfig {
    ...
    resValue "string", "youtube_api_key", "<YOUR_YOUTUBE_API_KEY>"
}
```

5. Change YouTube Channel ID:
To change the default YouTube channel in the app, replace the YouTube channel ID in the build.gradle file under defaultConfig:

```
defaultConfig {
    ...
    resValue "string", "youtube_channel_id", "<YOUR_CHANNEL_ID>"
}
```

6. Build and Run:
Once you've added your API key and channel ID, sync the project and build it. You can now run the app on an emulator or a physical device.

## Prerequisites 🛠️

Android Studio (latest stable version)
Gradle (ensure it's up to date)
YouTube Data API v3 key
## Technologies Used 🖥️

* Android: Native Android development
* YouTube Data API v3: For fetching videos and playlists from the YouTube channel
* Java/Kotlin: Android programming languages
* Retrofit: For making network calls
* Glide: For image loading
## Contributing 🤝

We welcome contributions! To contribute, follow these steps:

Fork the repository.
Create a new branch for your changes.
Make your changes and test them.
Submit a pull request with a detailed description of your changes.
Please make sure your code follows the project's coding style and includes any necessary tests.

## License 📜

This project is licensed under the MIT License - see the LICENSE file for details.

## Contact 📧

If you have any questions or suggestions, feel free to reach out to us:

* Author: Rabindra Khadka  
* Email: KhadkaRabindra27@gmail.com
