import { Component, OnInit, Input, AfterViewInit, Output } from '@angular/core';

@Component({
  selector: 'app-Odd',
  templateUrl: './Odd.component.html',
  styleUrls: ['./Odd.component.css'],
})
export class OddComponent implements OnInit {
  @Input() number: number;
  @Output() oddOrEven: string = '';

  constructor() {}

  ngOnInit() {
    console.log(this.number);
    if (this.number % 2 === 0) {
      this.oddOrEven = 'Even';
    } else {
      this.oddOrEven = 'Odd';
    }
  }
}
