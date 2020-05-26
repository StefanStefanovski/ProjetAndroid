import { Body, Controller, Get, Param, Post, Query } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { Category } from '../entities/comerce.entity';
import { ChatRoom } from '../entities/chatroom';
import { ChatMessage } from '../entities/chatmessage';
import { User } from '../entities/user.entity';


@Controller('chatroom')
export class ChatRoomController {
  constructor(@InjectRepository(ChatRoom)
              private chatroomRepo: Repository<ChatRoom>,

              @InjectRepository(ChatMessage)
              private chatMessageRepo: Repository<ChatMessage>,

              @InjectRepository(User)
              private userRepo: Repository<User>
  ) {
  }


  @Get('')
  async getRooms(): Promise<any> {
    return this.chatroomRepo.find({
      relations: ['owner']
    });
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


  @Get('hasaccess')
  async hasAccess(@Query() query): Promise<any> {
    let user = await this.userRepo.findOne({
      where: {
        id: query.user_id,
      },
      relations: ['requestedjoinChatRoom']
    });

    return user.requestedjoinChatRoom;
  }

  @Post('request')
  async requestAccess(@Body() query): Promise<any> {
    let room = await this.chatroomRepo.findOne({
      where: {
        id: query.room_id,
      },

      relations: ["requestedjoin"]
    });
    let user = await this.userRepo.findOne(query.user_id);

    room.requestedjoin.push(user);

    return this.chatroomRepo.save(room);
  }

  @Get('requests')
  async requests(@Body() query): Promise<any> {
    let room = await this.chatroomRepo.findOne({
      where: {
        id: query.room_id,
      },

      relations: ["requestedjoin"]
    });

    return room.requestedjoin;
  }


  @Post('messages')
  async addMessage(@Body() params): Promise<any> {
    return this.chatMessageRepo.insert(params);
  }

  @Post('accept-request')
  async acceptRequest(@Body() params): Promise<any> {
    let chatRoom = await this.chatroomRepo.findOne(params.id);

    chatRoom.joinedChatRoom = chatRoom.requestedjoin;
    chatRoom.joinedChatRoom = []

    this.chatroomRepo.save(chatRoom);
  }

}
