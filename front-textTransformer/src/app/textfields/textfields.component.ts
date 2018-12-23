import {Component, OnInit} from '@angular/core';
import {TextService} from '../text.service';
import {TextTransformed} from '../models/text-transformed';
import {HttpErrorResponse} from '@angular/common/http';
import {MatSnackBar} from '@angular/material';

@Component({
  selector: 'app-textfields',
  templateUrl: './textfields.component.html',
  styleUrls: ['./textfields.component.css']
})
export class TextfieldsComponent implements OnInit {

  inputText: string;
  outputText: TextTransformed;
  requestPath: string;
  selectedFunction: string;

  functions: string[] = [
    'upper',
    'lower',
    'capitalize',
    'reverse',
    'num2String',
    'abbreviate',
    'encode',
    'decode',
    'encodeBinary',
    'decodeBinary',
    'removeDuplicates'
  ];

  constructor(private textService: TextService,
              public snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.inputText = 'example input';
    this.selectedFunction = 'reverse';
  }

  reuseOutput() {
    this.inputText = this.outputText.textTransformed;
  }


  transform(chosenFunction: string, inputText: string) {
    console.log(chosenFunction, inputText);
    this.requestPath = 'text=' + inputText + '&function=' + chosenFunction;
    console.log(this.requestPath);
    this.textService.getOutputText(this.requestPath)
      .subscribe(outputText => this.outputText = outputText,
        error => {
        this.openSnackBar('Error!');
        if (error instanceof  HttpErrorResponse) {
            if (error.status === 400) {
              this.openSnackBar(error.error.message);
            }
            if (error.status === 500 && chosenFunction === 'decodeBinary') {
              this.openSnackBar('Your string has to consist of zeros and ones and be separated by spaces every 9 chars');
            }
          } else {
            this.openSnackBar('Error!');
          }
        });

  }

  openSnackBar(message: string) {
    const snackBarRef = this.snackBar.open(message, 'close', {
      verticalPosition: 'top',
      horizontalPosition: 'center',
      duration : 10000
    });

    snackBarRef.onAction()
      .subscribe( () => snackBarRef.dismiss());

  }

}
