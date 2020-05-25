import { Body, Controller, Get, Param, Post, Query } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { Category } from '../entities/comerce.entity';
import { ChatRoom } from '../entities/chatroom';
import { ChatMessage } from '../entities/chatmessage';


@Controller('chatroom')
export class ChatRoomController {
  constructor(@InjectRepository(ChatRoom)
              private chatroomRepo: Repository<ChatRoom>,

              @InjectRepository(ChatMessage)
              private chatMessageRepo: Repository<ChatMessage>
  ) {
  }


  @Get('')
  async getRooms(): Promise<any> {
    return this.chatroomRepo.find();
  }

  @Post('')
  async createRoom(@Body() params): Promise<any> {
    return this.chatroomRepo.insert({
      name: params.name,
      owner: params.owner,
      public: params.public == 'true',
    });
  }

  @Get('messages')
  async getRoomMessage(@Query() query): Promise<any> {
    return this.chatMessageRepo.find({
      where: {
        room: query.id,
      }
    })
  }

  @Post('messages')
  async addMessage(@Body() params): Promise<any> {
    return this.chatMessageRepo.insert(params);
  }

}
