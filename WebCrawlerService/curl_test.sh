cat curl_test.sh
curl --location --request POST 'http://localhost:6363/crawl' \
--header 'Content-Type: application/json' \
--data-raw '{ "url": "https://redhat.com","depth": 2,"domainOnly": true}'
