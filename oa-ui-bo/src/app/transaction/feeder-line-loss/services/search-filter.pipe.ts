import {Pipe, PipeTransform} from '@angular/core';

@Pipe({name: 'searchfilter'})

export class SearchFilterPipe implements PipeTransform {

    transform(value:any, searchText:String): any {

        if(!value) return [];
        if(!searchText) return value;

        return value.filter((value) => {
            return value.toString.toLowerCase().includes(searchText);
        })
    }
}