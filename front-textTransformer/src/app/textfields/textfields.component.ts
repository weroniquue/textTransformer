import { Component, OnInit } from '@angular/core';
import { TextService } from '../text.service';
import { TextTransformed } from '../models/text-transformed';

@Component({
  selector: 'app-textfields',
  templateUrl: './textfields.component.html',
  styleUrls: ['./textfields.component.css']
})
export class TextfieldsComponent implements OnInit {

  inputText: string;
  outputText: TextTransformed;

  constructor(private textService: TextService) { }

  ngOnInit() {
    this.getOutputText();
  }

  onButtonClick() {
    this.outputText.textTransformed += '.';
  }

  getOutputText(): void {
    //this.outputText = this.textService.getOutputText(); //call this in a function that responds on click instead of ngOnInit
  
    this.textService.getOutputText().subscribe(outputText => this.outputText = outputText);
  }



  // request wyglada tak: http://localhost:8080/TextTransformer/transform?text=aaa&function=upper
}
