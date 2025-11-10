import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'removeDash'
})
export class RemoveDashPipe implements PipeTransform {

  transform(value: string | undefined | null): string | undefined | null {
    const result: string | undefined | null = value == "last-step" ? "place order" : value?.split("-").join(" ");
    return result;
  }

}
