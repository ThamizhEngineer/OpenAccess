#!/bin/sh

rm -rf dist oa
rm oa.tar.gz
node --max_old_space_size=4096 ./node_modules/@angular/cli/bin/ng build --prod --aot=false --base-href "/oa/" --deploy-url "/oa/"
#ng build --prod --aot=false --base-href "/oa/" --deploy-url "/oa/"
mv dist oa
tar -czvf oa.tar.gz oa