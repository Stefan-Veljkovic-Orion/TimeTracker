from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
from typing import Literal

from .generate_activities import generate_and_send_activities
from .generate_employees import generate_and_send_employees


app = FastAPI(title="AI Data Generator")


class GenerateRequest(BaseModel):
    num: int = 5


class GenerateResponse(BaseModel):
    status: Literal["ok", "error"]
    requested: int
    inserted: int | None = None
    detail: str | None = None


@app.post("/ai/activities/generate", response_model=GenerateResponse)
async def generate_activities_endpoint(body: GenerateRequest) -> GenerateResponse:
    """
    Frontend poziv:
      POST http://localhost:8001/ai/activities/generate
      { "num": 5 }
    """
    try:
        inserted = generate_and_send_activities(body.num)
        return GenerateResponse(
            status="ok",
            requested=body.num,
            inserted=inserted,
        )
    except Exception as exc:
        raise HTTPException(status_code=500, detail=str(exc))


@app.post("/ai/employees/generate", response_model=GenerateResponse)
async def generate_employees_endpoint(body: GenerateRequest) -> GenerateResponse:
    """
    Frontend poziv:
      POST http://localhost:8001/ai/employees/generate
      { "num": 5 }
    """
    try:
        inserted = generate_and_send_employees(body.num)
        return GenerateResponse(
            status="ok",
            requested=body.num,
            inserted=inserted,
        )
    except Exception as exc:
        raise HTTPException(status_code=500, detail=str(exc))


if __name__ == "__main__":
    import uvicorn

    uvicorn.run(app, host="0.0.0.0", port=8001, log_level="info")
