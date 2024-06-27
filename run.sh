docker-compose -f heal/healenium/docker-compose-appium.yaml up -d
java -jar heal/appium_hub_and_node/selenium-server-standalone-3.9.1.jar -role hub -newSessionWaitTimeout 25000
appium -p 4723 --nodeconfig heal/appium_hub_and_node/nodeconfigdevice1.json -a 127.0.0.1 -pa /wd/hub