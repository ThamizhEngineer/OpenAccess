#!/bin/sh

rm -rf dist tneb-oa
rm tneb-oa.tar.gz
node --max_old_space_size=1096 ./node_modules/@angular/cli/bin/ng build --output-hashing=all --prod --aot=false --base-href "/tneb-oa/" --deploy-url "/tneb-oa/"
mv dist tneb-oa
tar -czvf tneb-oa.tar.gz tneb-oa