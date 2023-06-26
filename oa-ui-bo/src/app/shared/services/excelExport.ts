import { Injectable } from '@angular/core';
import * as FileSaver from 'file-saver';
import * as XLSX from 'xlsx-js-style';

import {Component, Input, Output, EventEmitter} from '@angular/core'
import { MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule, MatStepperModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule } from '@angular/material';
import { CommonModule } from '@angular/common';
import { FlexLayoutModule } from '@angular/flex-layout';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';


const EXCEL_EXTENSION = '.xlsx';
const EXCEL_TYPE = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8';


@Injectable()
export class ExcelExportService {
  constructor() { }
  public exportAsExcelFile(json: any[], excelFileName: string): void { 
    const worksheet: XLSX.WorkSheet = XLSX.utils.json_to_sheet(json);
    
    for (var i in worksheet) {
      if (typeof worksheet[i] != 'object') continue;
      let cell = XLSX.utils.decode_cell(i);
      
    worksheet[i].s = {
      // styling for all cells
      font: {
        name: 'arial',
        sz: "10.5",
      },
  
      border: {
        right: {
          style: 'thick',
          color: '000000',
        },
        left: {
          style: 'thick',
          color: '000000',
        },
        bottom: {
          style: 'thick',
          color: '000000',
        },
        top: {
          style: 'thick',
          color: '000000',
        }
    } 
    };

    if (worksheet[i].t=="z") {
      // first row
      worksheet[i].t="s";
      worksheet[i].v="";
      worksheet[i].s= {
        // bottom border
        border: {
          right: {
            style: 'thick',
            color: '000000',
          },
          left: {
            style: 'thick',
            color: '000000',
          },
          bottom: {
            style: 'thick',
            color: '000000',
          },
          top: {
            style: 'thick',
            color: '000000',
          }
         }
    };
    }

    if (cell.r == 0) {
      // first row
      worksheet[i].s= {
        // bottom border
        font: {
          bold: true,
          sz:"10.5",
        },
        border: {
          right: {
            style: 'thick',
            color: '000000',
          },
          left: {
            style: 'thick',
            color: '000000',
          },
          bottom: {
            style: 'thick',
            color: '000000',
          },
          top: {
            style: 'thick',
            color: '000000',
          }
         }
    };
    }
  }
    const workbook: XLSX.WorkBook = { Sheets: { 'Sheet1': worksheet}, SheetNames: ['Sheet1'] };
    
    const excelBuffer: any = XLSX.write(workbook, { bookType: 'xlsx', type: 'array'});
    this.saveAsExcelFile(excelBuffer, excelFileName);
  }
  private saveAsExcelFile(buffer: any, fileName: string): void {
    const data: Blob = new Blob([buffer], {
      type: EXCEL_TYPE
    });
    FileSaver.saveAs(data, fileName + '_export_' + new Date().getTime() + EXCEL_EXTENSION);
  }
}