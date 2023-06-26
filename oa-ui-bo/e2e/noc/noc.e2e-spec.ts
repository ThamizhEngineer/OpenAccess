import { browser, element, by } from 'protractor';
let navUrl: string;
let save = element(by.id("save"));
var testvalues = ['123', '567', 'TEST BY JASMINE', 'TEST BY PROTRACTOR'];

describe('NOC', () => {
    beforeEach(function () {
        browser.get('/noc');
    });

    it('Should click a first row', () => {
        let rows = element.all(by.buttonText("Open"));
		
		expect(element(by.css('md-card-title')).getText()).toContain('Existing NOC Applications');
        expect(rows.count()).toBe(4);

        let firstElem = rows.get(1);
        firstElem.click();
        browser.driver.sleep(100).then(function () {
            browser.driver.getCurrentUrl().then(function (url) {
                expect(url).toContain("noc-edit");
                navUrl = url;
            });
        });
    });

    describe('NOC EDIT', () => {
        beforeEach(function () {
			browser.waitForAngular();
			browser.get(navUrl);
		});
		it('Should load edit page', function () {
            
            expect(element(by.css('md-card-title')).getText()).toContain('Update NOC Application');
            var input = element(by.css('input[placeholder="Application Number"]'));
            expect(input.getAttribute('value')).toBe('TMP-02');
        });
		
		it('Save edited values', function () {
            
			var sd = element(by.css('input[placeholder="Sanctioned Demand"]'));
			sd.clear();
			sd.sendKeys(testvalues[0]);
            expect(sd.getAttribute('value')).toBe(testvalues[0]);
			
			var cd = element(by.css('input[placeholder="Contracted Demand"]'));
			cd.clear();
			cd.sendKeys(testvalues[1]);
            expect(cd.getAttribute('value')).toBe(testvalues[1]);
			
			var ccd = element(by.css('textarea[placeholder="Court Case Details"]'));
			ccd.clear();
			ccd.sendKeys(testvalues[2]);
            expect(ccd.getAttribute('value')).toBe(testvalues[2]);

			expect(save.isEnabled()).toBeTruthy();
			browser.actions().click(save).perform();
			
			var currentUrl;
			browser.getCurrentUrl().then(function (url) {
				currentUrl = url;
			}).then(function () {
				return browser.wait(function () {
					return browser.getCurrentUrl().then(function (url) {
						return url;
					});
				}, 1000);
			}).then(function () {
				browser.driver.getCurrentUrl().then(function (url) {
					expect(url).toContain("noc-list");
				});
			});
			
        });
    });

	describe('NOC EDIT RETEST', () => {
        beforeEach(function () {
			browser.waitForAngular();
			browser.get(navUrl);
		});
				
		it('Saved Values', function () {
            
			var sd = element(by.css('input[placeholder="Sanctioned Demand"]'));
			expect(sd.getAttribute('value')).toBe(testvalues[0]);
			
			var cd = element(by.css('input[placeholder="Contracted Demand"]'));
			expect(cd.getAttribute('value')).toBe(testvalues[1]);
			
			var ccd = element(by.css('textarea[placeholder="Court Case Details"]'));
			expect(ccd.getAttribute('value')).toBe(testvalues[2]);
			
        });
    });
});