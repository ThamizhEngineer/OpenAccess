// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `angular-cli.json`.

export const environment = {
  production: false, 
  clientId: "OA",
  authApiUrl:'http://10.251.45.85/oa-auth-service/',
  // authApiUrl:'http://localhost:4212/oa-auth-service/',
  //serviceApiUrl:'http://35.185.177.10/oa-service/',
  serviceApiUrl:'http://localhost:4206/oa-service/',
  reportUrl:'http://10.251.45.85/oa-report-service/',
  documentUrl:'http://localhost:4216/oa-document-service/',
  integrationUrl:'http://localhost:4208/oa-integration-service/',
  assetsApiUrl:'http://localhost:4200',
  cryptoKey:'openaccesstneb12',
  dataSource: 'api'  // api or json
  // authApiUrl:'http://10.8.0.20/oa-auth-service/',
  // // authApiUrl:'http://localhost:4212/oa-auth-service/',
  // //serviceApiUrl:'http://35.185.177.10/oa-service/',
  // serviceApiUrl:'http://10.8.0.20/oa-service/',
  // reportUrl:'http://10.8.0.20/oa-report-service/',
  // documentUrl:'http://10.8.0.20/oa-document-service/',
  // assetsApiUrl:'http://localhost:4200',
  // cryptoKey:'openaccesstneb12'
};